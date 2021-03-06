/*
 * MIT License
 *
 * Copyright (c) 2017 TU Wien
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.horndroid.z3;


import com.horndroid.debugging.QUERY_TYPE;
import com.microsoft.z3.BoolExpr;


public class Z3Query {

    private BoolExpr query;
    private String description;
    private boolean isVerbose;
    public boolean debugging;
    public boolean isReg;
    public boolean isLocalHeap;
    
    public String allocationClassName;
    public String allocationMethodeName;
    public int field = 0;
    public int allocationPC;
    public int regNum = 0;
    public QUERY_TYPE queryType;
    public int instanceNum;

    
    private String className;
    private String methodName;
    private String pc;
    private String sinkName;

    public Z3Query(BoolExpr query, String desc, boolean verbose,
                   String className, String methodName, String pc, String sinkName){
        this.query = query;
        this.description = desc;
        this.isVerbose = verbose;
        this.debugging = false;
        this.isReg = false;
        this.isLocalHeap = false;
        
        this.field = 99999;
        this.allocationClassName = "Error Z3QUery3";
        this.allocationMethodeName = "Error Z3QUery4";
        
        this.className = className;
        this.methodName = methodName;
        this.pc = pc;
        this.sinkName = sinkName;
    }

    /*
     * Debug queries for registers
     */
    public Z3Query(BoolExpr query, int regNum, QUERY_TYPE queryType,
            String className, String methodName, String pc){
        this.query = query;
        this.description = null;
        this.isVerbose = false;
        this.debugging = true;
        this.isReg = true;
        this.isLocalHeap = false;
        
        this.regNum = regNum;
        this.queryType = queryType;
        
        this.field = 99999;
        this.allocationClassName = "Error Z3QUery";
        this.allocationMethodeName = "Error Z3QUery2";
        
        this.className = className;
        this.methodName = methodName;
        this.pc = pc;
        this.sinkName = null;
    }
    
    /*
     * Debuq queries for local heap values
     */
    public Z3Query(BoolExpr query, String acn, String amn, int apc, int af, int instanceNum, QUERY_TYPE queryType,
            String className, String methodName, String pc){
        this.query = query;
        this.description = null;
        this.isVerbose = false;
        this.debugging = true;
        this.isReg = false;
        this.isLocalHeap = true;
        
        this.allocationClassName = acn;
        this.allocationMethodeName = amn;
        this.allocationPC = apc;
        this.field = af;
        this.queryType = queryType;
        this.instanceNum = instanceNum;

        this.className = className;
        this.methodName = methodName;
        this.pc = pc;
        this.sinkName = null;
    }
    

    public BoolExpr getQuery() {
        return query;
    }

    public void setQuery(BoolExpr query) {
        this.query = query;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVerbose() {
        return isVerbose;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getPc() {
        return pc;
    }

    public String getSinkName() {
        return sinkName;
    }

}
