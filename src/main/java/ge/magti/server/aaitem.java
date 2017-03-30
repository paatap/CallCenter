package ge.magti.server;

/**
 * Created by user on 3/30/17.
 */

/*
 * Isomorphic SmartGWT web presentation layer
 * Copyright 2000 and beyond Isomorphic Software, Inc.
 *
 * OWNERSHIP NOTICE
 * Isomorphic Software owns and reserves all rights not expressly granted in this source code,
 * including all intellectual property rights to the structure, sequence, and format of this code
 * and to all designs, interfaces, algorithms, schema, protocols, and inventions expressed herein.
 *
 *  If you have any questions, please email <sourcecode@isomorphic.com>.
 *
 *  This entire comment must accompany any portion of Isomorphic Software source code that is
 *  copied or moved from this file.
 */



        import java.io.Serializable;
        import java.util.Date;

// a typical Java Bean which can be stored by many different ORM (object-relational mapping)
// systems, including Hibernate, Toplink, JDO, EJB3, etc.
public class aaitem implements Serializable {
    // a zero-argument constructor is not required, but does enable certain convenience
    // features (see the docs for DMI)
    public aaitem() { }

    // when receiving data from client-side SmartClient components, SmartClient will call these
    // setters to modify properties.  The setters are found via the Java Beans naming
    // convention, for example, a DataSource field named "category" will be applied via a
    // setter called setCategory().
    public void setItemID(Long id) { oid = id; }
    public void seta1(String _a1) { a1 = _a1; }


    // SmartClient will call these getters when serializing a Java Bean to be transmitted to
    // client-side components.
    public Long getItemID() { return oid; }
    public String geta1() { return a1; }


    // this bean has no business logic.  It simply stores data in these instance variables.
    protected Long oid;
    protected String a1;

}