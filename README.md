## Synopsis

A very simple generic state machine in java. 

## Code Example
```private class StateCounterState implements IStateDataStructureState< String >{
    private String _stateId;
    private String _stateName;
    private String _imagePath;

    public StateCounterState( String id, String name, String imagePath ) {
        this._stateId = id;
        this._stateName = name;
        this._imagePath = imagePath;
    }
    public String getName() {
        return _stateName;
    }
}

// set up machine
// ctrl-E
KeyStroke elrKeyStroke = KeyStroke.getKeyStroke( 69, 130 );
_stateMachine = new StateMachine< String, KeyStroke, StateCounterState >();
_stateMachine
    .addState( new StateCounterState( "4-5-8", "4-5-8 E Sq", "ru/ru458S"))
    .addState( new StateCounterState( "4-4-7", "4-4-7 1 Sq", "ru/ru447S"))
    .addTransition(elrKeyStroke, "4-5-8", "4-4-7" )
    .setState( "4-5-8" );

// should print "4-5-8 E Sq"
System.out.println( _stateMachine.getCurrentState().getName() );
// perform a transition
_stateMachine.transition( elrKeyStroke );
// should print "4-4-7 1 Sq"
System.out.println( _stateMachine.getCurrentState().getName() );
```

## Motivation

The state machine libraries I researched were way more than I needed. I just
needed to keep track of some state based on a chain of transitions.

## Installation

gradle jar

## API Reference

All methods are shown in the code example above

## Tests

gradle test

## License

Usage is subject to the terms of the Library Gnu Public Licesne (LGPL) available from http://www.opensource.org.


