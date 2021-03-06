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

package com.horndroid.Dalvik;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Class used to store the Analysis DalvikInstances
 * The instances are indexed by the hashcode of the type's name string for efficiency reasons
 */
public class Instances {
    final private Map<Integer,HashSet<DalvikInstance>> instances;
    
    public Instances(){
        instances = new ConcurrentHashMap<Integer,HashSet<DalvikInstance>>();
    }
    
    /*
     * Add the element to the set of instances
     */
    public void add(DalvikInstance di){
        int key = di.getType().getType().hashCode();
        if (!instances.containsKey(key)){
            instances.put(key,new HashSet<DalvikInstance>());
        }
        instances.get(key).add(di);
    }
    
    public HashSet<DalvikInstance> getByType(final int c){
        return instances.get(c);
    }
    
//    public Map<Integer,HashSet<DalvikInstance>> get(){
//        return (Map<Integer,HashSet<DalvikInstance>>) instances;
//    }
//  
    /*
     * Give a Set view of all instances. This operation may be a bit costly, and should be avoided as much as possible
     */
    public Set<DalvikInstance> getAllOnce(){
        HashSet<DalvikInstance> hset = new HashSet<DalvikInstance>();
        for (int hc : instances.keySet()){
            hset.addAll(instances.get(hc));
        }
        return hset;
    }

    /*
     * Change the instance type to cd for all instances whose type name string is the same as the name string of cd.
     */
    public void changeType(DalvikClass cd) {
        int key = cd.getType().hashCode();
        if (instances.containsKey(key)){
            for (DalvikInstance di: instances.get(key)){
                di.changeType(cd);
            }
        }
    }

    /*
     * Return the number of instances stored
     */
    public int size() {
        int size = 0;
        for (int key : instances.keySet()){
            size += instances.get(key).size();
        }
        return size;
        
    }
    
//   /*
//    * Add super class instance 
//    */
//    private void addSuperInstance(final DalvikInstance di){
//        if (di.getType() instanceof DalvikClass){
//            final GeneralClass superClass = ((DalvikClass) di.getType()).getSuperClass();
//            if (superClass == null) return;
//            Set<DalvikInstance> superInstances = getByType(superClass.getType().hashCode());
//            if (superInstances.isEmpty()){
//                final DalvikInstance superInstance = new DalvikInstance(di.getC(), di.getM(), di.getPC(), superClass, true);
//                add(superInstance);
//                addSuperInstance(superInstance);
//            }
//            else{
//                boolean addedAlready = false;
//                for (final DalvikInstance si: superInstances){
//                    if ((si.getC() == di.getC()) && (si.getM() == di.getM()) && (si.getPC() == di.getPC())){
//                        addedAlready = true;
//                        break;
//                    }
//                }
//                if (!addedAlready){
//                    final DalvikInstance superInstance = new DalvikInstance(di.getC(), di.getM(), di.getPC(), superClass, true);
//                    add(superInstance);
//                    addSuperInstance(superInstance);
//                }
//            }
//        }
//    }
//   /*
//    * Add super class instances
//    */     
//    public void addSuperInstances(){
//        for (final DalvikInstance di: getAll()){
//            if (di.getType() instanceof DalvikClass){
//                    addSuperInstance(di);
//                }
//            }
//   }
}
