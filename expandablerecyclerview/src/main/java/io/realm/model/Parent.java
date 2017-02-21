package io.realm.model;

import java.util.List;

import io.realm.RealmModel;

/**
 * Interface for implementing required methods in a parent.
 */
public interface Parent<C extends Child> extends RealmModel {

    /**
     * Getter for the list of this parent's child items.
     * <p>
     * If list is empty, the parent has no children.
     *
     * @return A {@link List} of the children of this {@link Parent}
     */
    List<C> getChildList();

    /**
     * Getter used to determine if this {@link Parent}'s
     * {@link android.view.View} should show up initially as expanded.
     *
     * @return true if expanded, false if not
     */
    boolean isExpanded();
}