package org.my.adventure.dao_manager.api.dao;

import org.my.adventure.dao_manager.api.entities.Transition;

import java.util.List;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public interface TransitionDAO extends CommonDAO<Transition> {
    List<Transition> getTransitionsByNodeId(long id);
}
