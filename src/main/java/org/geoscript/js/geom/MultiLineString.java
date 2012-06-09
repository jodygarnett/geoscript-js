package org.geoscript.js.geom;

import java.util.Arrays;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.FunctionObject;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Wrapper;
import org.mozilla.javascript.annotations.JSConstructor;

public class MultiLineString extends GeometryCollection implements Wrapper {

    /** serialVersionUID */
    private static final long serialVersionUID = -4988339189326884593L;

    /**
     * The most recently created prototype.
     */
    static Scriptable prototype;

    public Class<?> restrictedType = LineString.class;

    /**
     * Prototype constructor.
     * @return 
     */
    public MultiLineString() {
    }

    /**
     * Constructor from JTS geometry.
     * @param geometry
     */
    public MultiLineString(Scriptable scope, com.vividsolutions.jts.geom.MultiLineString geometry) {
        if (prototype == null) {
            throw new RuntimeException("Prototype has not yet been set up by calling require('geoscript/geom') from a module");
        }
        this.setParentScope(scope);
        this.setPrototype(prototype);
        setGeometry(geometry);
    }

    /**
     * Constructor for coordinate array.
     * @param context
     * @param scope
     * @param array
     */
    public MultiLineString(NativeArray array) {
        super(array);
    }

    public com.vividsolutions.jts.geom.MultiLineString createCollection(com.vividsolutions.jts.geom.Geometry[] geometries) {
        com.vividsolutions.jts.geom.LineString[] lines = Arrays.copyOf(geometries, geometries.length, com.vividsolutions.jts.geom.LineString[].class);
        return new com.vividsolutions.jts.geom.MultiLineString(lines, factory);
    }

    /**
     * Finishes JavaScript constructor initialization.  
     * Sets up the prototype chain using superclass.
     * 
     * @param scope
     * @param ctor
     * @param prototype
     */
    public static void finishInit(Scriptable scope, FunctionObject ctor, Scriptable prototype) {
        MultiLineString.prototype = prototype;
    }
    

    /**
     * JavaScript constructor.
     * @param cx
     * @param args
     * @param ctorObj
     * @param inNewExpr
     * @return
     */
    @JSConstructor
    public static Object constructor(Context cx, Object[] args, Function ctorObj, boolean inNewExpr) {
        MultiLineString collection = null;
        Object arg = args[0];
        if (arg instanceof NativeArray) {
            collection = new MultiLineString((NativeArray) arg);
        }
        return collection;
    }

    /**
     * Returns underlying JTS geometry.
     */
    public com.vividsolutions.jts.geom.MultiLineString unwrap() {
        return (com.vividsolutions.jts.geom.MultiLineString) getGeometry();
    }

}
