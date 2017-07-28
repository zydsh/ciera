package microwaveovenciera.components.microwaveoven.microwaveoven;

import ciera.classes.EmptyInstance;
import ciera.classes.EmptyInstanceSet;
import ciera.classes.InstanceSet;
import ciera.classes.ModelInstance;
import ciera.classes.Where;
import ciera.exceptions.XtumlException;

@SuppressWarnings("serial")
public class DoorSet extends InstanceSet {

    // empty set
    public static final DoorSet emptyDoorSet = new EmptyDoorSet();

    // selections
    public OvenSet selectManyMO_OsOnR4() throws XtumlException {
        return selectManyMO_OsOnR4( null );
    }

    public OvenSet selectManyMO_OsOnR4( Where condition ) throws XtumlException {
        OvenSet return_set = new OvenSet();
        for ( ModelInstance door : this ) {
            Oven selected = ((Door)door).selectOneMO_OOnR4( condition );
            if ( !(selected instanceof EmptyInstance ) ) return_set.add( selected );
        }
        if ( return_set.isEmpty() ) return OvenSet.emptyOvenSet;
        else return return_set;
    }
    
    public Door selectAnyMO_DFromInstances( Where condition ) {
        return (Door)selectAny( condition );
    }
    
    public DoorSet selectManyMO_DsFromInstances( Where condition ) {
        return (DoorSet)selectMany( condition );
    }

    @Override
    public Door getEmptyInstance() {
        return Door.emptyDoor;
    }

}

@SuppressWarnings("serial")
class EmptyDoorSet extends DoorSet implements EmptyInstanceSet {

    // selections
    @Override
    public OvenSet selectManyMO_OsOnR4( Where condition ) throws XtumlException {
        return OvenSet.emptyOvenSet;
    }
    
    @Override
    public Door selectAnyMO_DFromInstances( Where condition ) {
        return Door.emptyDoor;
    }
    
    @Override
    public DoorSet selectManyMO_DsFromInstances( Where condition ) {
        return DoorSet.emptyDoorSet;
    }

}
