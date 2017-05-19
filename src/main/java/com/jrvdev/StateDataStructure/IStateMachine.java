package com.jrvdev.StateDataStructure;

public interface IStateMachine<IdType, TransitionType, StateData extends IStateDataStructureState<IdType> > {
    IStateMachine<IdType, TransitionType, StateData> addState( StateData newState );
    IStateMachine<IdType, TransitionType, StateData> addTransition( TransitionType transitionValue, IdType transitionFromId, IdType transitionToId );
    IStateMachine<IdType, TransitionType, StateData> setState( IdType id );
    StateData getCurrentState();
    IStateMachine<IdType, TransitionType, StateData> transition( TransitionType transitionValue );
}
