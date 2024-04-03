package com.intalink.configoperations.domain.pdm;

public class PdmInitClass<P extends OPackage, T extends OTable, C extends OColumn> {
    private Class<P> pClass;
    private Class<T> tClass;
    private Class<C> cClass;

    private PdmInitClass() {
    }

    public PdmInitClass(Class<P> pClass, Class<T> tClass, Class<C> cClass) throws Exception {
        this.cClass = cClass;
        this.pClass = pClass;
        this.tClass = tClass;
    }

    public Class<P> getpClass() {
        return this.pClass;
    }

    public Class<T> gettClass() {
        return this.tClass;
    }

    public Class<C> getcClass() {
        return this.cClass;
    }
}

