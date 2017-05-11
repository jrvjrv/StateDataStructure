package com.jrvdev.StateDataStructure;

import java.util.Map;
import java.util.HashMap;

public class StateMachine<IdType, TransitionType, StateData extends IStateDataStructureState<IdType> > {
    
    private class StateDataWrapper {
        private StateData _wrappedData;
        private Map< TransitionType, IdType > _transitions;
        
        
        public StateDataWrapper( StateData wrappedData ) {
            _wrappedData = wrappedData;
            _transitions = new HashMap< TransitionType, IdType >();
        }
        
        public StateData getStateData() {
            return _wrappedData;
        }
        
        public IdType getTransition( TransitionType transitionValue ) {
            return _transitions.get(transitionValue);
        }
        
        public StateDataWrapper addTransition( IdType toId, TransitionType transitionValue ) {
            _transitions.put(transitionValue, toId);
            return this;
        }
    }
    
    private StateDataWrapper _currentState;
    private Map< IdType, StateDataWrapper > _states;
    
    public StateMachine() {
        _states = new HashMap<IdType, StateDataWrapper>();
    }
    
    public StateMachine<IdType, TransitionType, StateData> addState( StateData newState ) {
        StateDataWrapper newWrapper = new StateDataWrapper( newState );
        _states.put( newState.getStateId(),  newWrapper );
        if ( _currentState == null ) {
            _currentState = newWrapper;
        }
        return this;
    }
    public StateMachine<IdType, TransitionType, StateData> addTransition( TransitionType transitionValue, IdType transitionFromId, IdType transitionToId ) {
        StateDataWrapper fromState = _states.get(transitionFromId);
        if ( fromState != null ) {
            fromState.addTransition(transitionToId, transitionValue);
        }
        return this;
    }
    public StateMachine<IdType, TransitionType, StateData> setState( IdType id ) {
        StateDataWrapper toMakeCurrent = _states.get(id);
        if ( toMakeCurrent != null ) {
            _currentState = toMakeCurrent;
        }
        return this;
    }
    public StateData getCurrentState() {
        return (_currentState == null ) ? ( null ) : ( _currentState.getStateData() );
    }
    
    public StateMachine<IdType, TransitionType, StateData> transition( TransitionType transitionValue ) {
        IdType toId = _currentState.getTransition(transitionValue);
        if ( toId != null ) {
            StateDataWrapper toState = _states.get(toId);
            if ( toState != null ) {
                _currentState = toState;
            }
        }
        return this;
    }
    
    public static<IdType, TransitionType, StateData extends IStateDataStructureState< IdType >> StateMachine<IdType, TransitionType, StateData> create() {
        return new StateMachine<IdType, TransitionType, StateData >();
    }
}
