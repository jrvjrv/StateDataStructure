package com.jrvdev.StateDataStructure;

import static org.junit.Assert.*;

import org.junit.Test;

import javax.swing.KeyStroke;

public class StateMachineTest {
    
    private class IntStateDataStructure implements IStateDataStructureState<Integer> {
        
        private Integer _id;
        
        public IntStateDataStructure( Integer id ) {
            _id = id;
        }

        @Override
        public Integer getStateId() {
            // TODO Auto-generated method stub
            return _id;
        }
        
    }

    @Test
    public void testCreate() {
        StateMachine<Integer, String, IntStateDataStructure> theMachine = StateMachine.create();
        
        assertNotNull(theMachine);
        
        System.out.println(theMachine.getClass().getName());
    }

    @Test
    public void testAddState() {
        StateMachine<Integer, String, IntStateDataStructure> theMachine = StateMachine.create();
        
        IntStateDataStructure theState = new IntStateDataStructure( 3 );
        
        theMachine.addState(theState);
        
        assertEquals(3, theMachine.getCurrentState().getStateId().intValue());
    }

    @Test
    public void testSetState() {
        StateMachine<Integer, String, IntStateDataStructure> theMachine = StateMachine.create();
        
        IntStateDataStructure theState3 = new IntStateDataStructure( 3 );
        theMachine.addState(theState3);
        
        IntStateDataStructure theState2 = new IntStateDataStructure( 2 );
        theMachine.addState(theState2);
        
        // initial state is first one added
        assertEquals(theState3.getStateId().intValue(), theMachine.getCurrentState().getStateId().intValue());
        
        // switch state to one that is present
        theMachine.setState(2);
        assertEquals(theState2.getStateId().intValue(), theMachine.getCurrentState().getStateId().intValue());

        // attempting to switch state to one not present leaves the machine in its original state
        theMachine.setState(11);
        assertEquals(theState2.getStateId().intValue(), theMachine.getCurrentState().getStateId().intValue());
        
    }
    
    @Test
    public void testTransition() {
        StateMachine<Integer, String, IntStateDataStructure> theMachine = StateMachine.create();
        
        // add three states, to mix things up
        IntStateDataStructure theState3 = new IntStateDataStructure( 3 );
        theMachine.addState(theState3);
        IntStateDataStructure theState2 = new IntStateDataStructure( 2 );
        theMachine.addState(theState2);
        IntStateDataStructure theState1 = new IntStateDataStructure( 1 );
        theMachine.addState(theState1);
        
        theMachine.addTransition("A", theState3.getStateId(), theState2.getStateId());
        assertEquals(theState3.getStateId().intValue(), theMachine.getCurrentState().getStateId().intValue());
        
        theMachine.transition("A");
        assertEquals(theState2.getStateId().intValue(), theMachine.getCurrentState().getStateId().intValue());
    }
    
    @Test
    public void test_complicated_transition() {
        StateMachine<Integer, String, IntStateDataStructure> theMachine = StateMachine.create();
        
        // add three states, to mix things up
        IntStateDataStructure theState3 = new IntStateDataStructure( 3 );
        theMachine.addState(theState3);
        IntStateDataStructure theState2 = new IntStateDataStructure( 2 );
        theMachine.addState(theState2);
        IntStateDataStructure theState1 = new IntStateDataStructure( 1 );
        theMachine.addState(theState1);
        
        theMachine.addTransition("A", theState3.getStateId(), theState2.getStateId());
        theMachine.addTransition("A", theState2.getStateId(), theState1.getStateId());
        theMachine.addTransition("C", theState2.getStateId(), theState3.getStateId());
        theMachine.addTransition("A", theState1.getStateId(), theState3.getStateId());
        assertEquals(theState3.getStateId().intValue(), theMachine.getCurrentState().getStateId().intValue());

        theMachine.transition("A").transition("A");
        assertEquals(theState1.getStateId(), theMachine.getCurrentState().getStateId());

        theMachine.transition("A").transition("A");
        assertEquals(theState2.getStateId(), theMachine.getCurrentState().getStateId());
        
        theMachine.transition("C");
        assertEquals(theState3.getStateId().intValue(), theMachine.getCurrentState().getStateId().intValue());
        
    }
    
    // double-check that HashMap uses something like .equals()
    @Test
    public void keystroke_for_transition_type() {
        StateMachine<Integer, KeyStroke, IntStateDataStructure> theMachine = StateMachine.create();
        
        // add three states, to mix things up
        IntStateDataStructure theState3 = new IntStateDataStructure( 3 );
        theMachine.addState(theState3);
        IntStateDataStructure theState2 = new IntStateDataStructure( 2 );
        theMachine.addState(theState2);
        IntStateDataStructure theState1 = new IntStateDataStructure( 1 );
        theMachine.addState(theState1);
        
        theMachine.addTransition(KeyStroke.getKeyStroke('A'), theState3.getStateId(), theState2.getStateId());
        theMachine.addTransition(KeyStroke.getKeyStroke('A'), theState2.getStateId(), theState1.getStateId());
        // ctrl-C, not plain C
        theMachine.addTransition(KeyStroke.getKeyStroke(new Character('C'), java.awt.event.InputEvent.CTRL_MASK), theState2.getStateId(), theState3.getStateId());
        theMachine.addTransition(KeyStroke.getKeyStroke('A'), theState1.getStateId(), theState3.getStateId());
        assertEquals(theState3.getStateId().intValue(), theMachine.getCurrentState().getStateId().intValue());

        theMachine.transition(KeyStroke.getKeyStroke('A')).transition(KeyStroke.getKeyStroke('A'));
        assertEquals(theState1.getStateId(), theMachine.getCurrentState().getStateId());

        theMachine.transition(KeyStroke.getKeyStroke('A')).transition(KeyStroke.getKeyStroke('A'));
        assertEquals(theState2.getStateId(), theMachine.getCurrentState().getStateId());

        // no transition on plain C
        theMachine.transition(KeyStroke.getKeyStroke('C'));
        assertEquals(theState2.getStateId(), theMachine.getCurrentState().getStateId());
        // yes transition ctrl-C
        theMachine.transition(KeyStroke.getKeyStroke(new Character('C'), java.awt.event.InputEvent.CTRL_MASK));
        assertEquals(theState3.getStateId().intValue(), theMachine.getCurrentState().getStateId().intValue());
        
    }
    
    
    @Test
    public void cant_add_a_second_state_with_same_id() {
    }
    
    // cant add transition to non-existent state
    // cant add transition from non-existent state
    // cant replace existing transition


/*
    @Test
    public void testAddTransition() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetCurrentState() {
        fail("Not yet implemented");
    }

    @Test
    public void testTransition() {
        fail("Not yet implemented");
    }
*/
}
