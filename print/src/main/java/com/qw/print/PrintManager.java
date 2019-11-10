package com.qw.print;

import com.qw.print.core.PrintRequest;
import com.qw.print.core.PrintTask;
import com.qw.print.utils.PLog;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by qinwei on 2019/1/3 10:14 AM
 * email: qin.wei@mwee.cn
 */

public class PrintManager implements PrintTask.OnPrintTaskListener {
    private static PrintManager mInstance;
    private final PrintConfig config;
    private ExecutorService mExecutors;
    public LinkedBlockingQueue<PrintRequest> mPrintWaitQueues;
    public HashMap<String, PrintTask> mPrintTasks;

    private PrintManager(PrintConfig config) {
        this.config = config;
        PrintContext.setContext(config.getContext());
        mExecutors = Executors.newFixedThreadPool(10);
        mPrintWaitQueues = new LinkedBlockingQueue<>();
        mPrintTasks = new HashMap<>();
        PLog.d("print manager init");
    }

    public static PrintManager getInstance() {
        if (mInstance == null) {
            throw new IllegalArgumentException("must call init method to init PrintManager");
        }
        return mInstance;
    }

    public static void init(PrintConfig config) {
        if (mInstance == null) {
            mInstance = new PrintManager(config);
        }
    }

    public void addPrint(PrintRequest request) {
        if (mPrintTasks.size() > config.getMaxPrintTaskSize()) {
            mPrintWaitQueues.offer(request);
        } else {
            execute(request);
        }
    }

    private void execute(PrintRequest request) {
        PrintTask task = new PrintTask(request);
        task.setOnPrintTaskListener(this);
        mPrintTasks.put(request.tag, task);
        mExecutors.execute(task);
    }

    @Override
    public void onStart(String tag) {
        PLog.d("print onStart tag:" + tag);
    }

    @Override
    public void onCompleted(String tag) {
        mPrintTasks.remove(tag);
        PLog.d("print onCompleted tag:" + tag + ",mPrintWaitQueues size:" + mPrintWaitQueues.size());
        if (mPrintWaitQueues.size() > 0) {
            execute(mPrintWaitQueues.poll());
        }
    }
}