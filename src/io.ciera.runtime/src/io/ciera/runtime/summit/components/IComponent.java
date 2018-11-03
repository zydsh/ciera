package io.ciera.runtime.summit.components;

import io.ciera.runtime.IVersioned;
import io.ciera.runtime.summit.application.IActionHome;
import io.ciera.runtime.summit.classes.IInstancePopulation;
import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.types.IXtumlType;

public interface IComponent<C extends IComponent<C>> extends IInstancePopulation, IActionHome<C>, IXtumlType<C>, IVersioned {

    public void initialize() throws XtumlException;

}