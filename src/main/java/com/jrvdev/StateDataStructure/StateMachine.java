package com.jrvdev.StateDataStructure;

public class StateMachine<IdType, TransitionType, StateData extends IState<IdType> > {
    public StateMachine<IdType, TransitionType, StateData> addState( IdType id, StateData data ) {
        return this;
    }
    public StateMachine<IdType, TransitionType, StateData> addTransition( TransitionType transitionValue, IdType transitionFromId, IdType transitionToId ) {
        return this;
    }
    public StateMachine<IdType, TransitionType, StateData> setState( IdType id ) {
        return this;
    }
    public StateData getCurrentState() {
        return null;
    }
    
    public StateMachine<IdType, TransitionType, StateData> transition( TransitionType transitionValue ) {
        return this;
    }
    
    public static<IdType, TransitionType, StateData extends IState< IdType >> StateMachine<IdType, TransitionType, StateData> create() {
        return new StateMachine<IdType, TransitionType, StateData >();
    }
}
