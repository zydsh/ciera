package microwaveovenciera.components.microwaveoven.microwaveoven;

import java.util.UUID;

import ciera.classes.EmptyInstance;
import ciera.classes.ModelInstance;
import ciera.classes.Where;
import ciera.exceptions.EmptyInstanceException;
import ciera.exceptions.LinkException;
import ciera.exceptions.ModelIntegrityException;
import ciera.exceptions.XtumlException;
import microwaveovenciera.components.microwaveoven.microwaveoven.turntable.instancestatemachine.TurntableInstanceStateMachine;

public class Turntable extends ModelInstance {
    
    private static final int classNumber = 6;
    private static final String keyLetters = "MO_TRN";
    
    // empty instance
    public static final Turntable emptyTurntable = new EmptyTurntable();
    
    // class attributes
    private UUID m_TurntableID;
    
    // associations
    private Oven MO_OOnR5;
    
    public void setMO_OOnR5(Oven mO_OOnR5) throws XtumlException {
        if ( null == MO_OOnR5 ) MO_OOnR5 = mO_OOnR5;
        else throw new LinkException( "Cannot link to already linked relationship." );
    }

    public void clearMO_OOnR5() throws XtumlException {
        if ( null != MO_OOnR5 ) MO_OOnR5 = null;
        else throw new LinkException( "Cannot unlink non-linked relationship." );
    }
    
    // constructors
    public Turntable() {
        super( new TurntableInstanceStateMachine() );
        m_TurntableID = UUID.randomUUID();
    }
    
    // attribute accessors
    public UUID getM_TurntableID() throws XtumlException {
        checkLiving();
        return m_TurntableID;
    }

    // selections
    public Oven selectOneMO_OOnR5() throws XtumlException {
        return selectOneMO_OOnR5( null );
    }
    
    public Oven selectOneMO_OOnR5( Where condition ) throws XtumlException {
        checkLiving();
        if ( null == MO_OOnR5 ) throw new ModelIntegrityException( "Uncoditional association with no related instance." );
        else if ( null == condition || condition.evaluate(MO_OOnR5) ) return MO_OOnR5;
        else return Oven.emptyOven;
    }

    // relates
    public void relateToMO_OAcrossR5( Oven oven ) throws XtumlException {
        oven.relateToMO_TRNAcrossR5( this );
    }
    
    // unrelates
    public void unrelateFromMO_OAcrossR5( Oven oven ) throws XtumlException {
        oven.unrelateFromMO_TRNAcrossR5( this );
    }

    @Override
    public int getClassNumber() {
        return classNumber;
    }
    
    @Override
    public String getKeyLetters() {
        return keyLetters;
    }

}

class EmptyTurntable extends Turntable implements EmptyInstance {

    // selections
    @Override
    public Oven selectOneMO_OOnR5( Where condition ) throws XtumlException {
        return Oven.emptyOven;
    }

    // relates
    @Override
    public void relateToMO_OAcrossR5( Oven oven ) throws XtumlException {
        throw new EmptyInstanceException( "Relate of empty instance" );
    }

    @Override
    public void setMO_OOnR5(Oven mO_OOnR5) throws XtumlException {
        throw new EmptyInstanceException( "Relate of empty instance" );
    }
    
    // unrelates
    @Override
    public void unrelateFromMO_OAcrossR5( Oven oven ) throws XtumlException {
        throw new EmptyInstanceException( "Unrelate of empty instance" );
    }

    @Override
    public void clearMO_OOnR5() throws XtumlException {
        throw new EmptyInstanceException( "Unrelate of empty instance" );
    }

}
