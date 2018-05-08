package com.lingkj.android.library.recycle;

/**
 * @author panlijun
 */


public interface LiveData {
    boolean areItemTheSame(LiveData data);

    boolean areContentTheSame(LiveData data);
}
