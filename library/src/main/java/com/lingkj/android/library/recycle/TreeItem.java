package com.lingkj.android.library.recycle;

import java.util.List;

/**
 * @author panlijun
 */


public interface TreeItem {
    boolean isExpanded();
    boolean hasChildren();
    int getLevel();
    List<TreeItem> getChildren();
}
