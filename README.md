# IntelligentSIDC_JAVA_ADT
- Student Identification Code, an implementation of java ADT for O(1) to O(n) for insert, update, delete, find operations for large scale data

The Canadian Student Tracking Agency (CSTA) maintains and operates on multiple lists of n students. Each student is identified by a unique, code called StudentIDentificationCode (SIDC), of 8 digits (e.g. # SIDC: 47203235), some of the lists are local for villages, towns and remote areas, where n counts only to few hundred students, and possibly less. Others are at the urban cities or provincial levels, where n counts to tens of thousands or more. CSTA needs your help to design an intelligent “student tracking” ADT called IntelligentSIDC. Keys of IntelligentSIDC entries are long integers of 8 digits, and one can retrieve the keys/values of an IntelligentSIDC or access a single element by its key. Furthermore, similar to Sequences, given a IntelligentSIDC key, one can access its predecessor or successor (if it exists).

IntelligentSIDC adapts to their usage and keep the balance between memory and runtime requirements. For instance, if an IntelligentSIDC contains only a small number of entries (e.g., few hundreds), it might use less memory overhead but slower (sorting) algorithms. On the other hand, if the number of contained entries is large (greater than 1000 or even in the range of tens of thousands of elements), it might have a higher memory requirement but faster (sorting) algorithms. IntelligentSIDC might be almost constant in size or might grow and/or shrink dynamically. Ideally, operations applicable to a single IntelligentSIDC entry should be O(1) but never worse than O(n). Operations applicable to a complete IntelligentSIDC should not exceed O(n2).

You have been asked to design and implement the IntelligentSIDC ADT, which automatically adapts to the dynamic content that it operates on. In other words, it accepts the size (total number of students, n, identified by their 8 digits SIDC number as a key) as a parameter and uses an appropriate (set of) data structure(s), or other data types, from the one(s) studied in class in order to perform the operations below efficiently1. You are NOT allowed however to use any of the built-in data types (that is, you must implement whatever you need, for instance, linked lists, expandable arrays, hash tables, etc. yourself).

### The IntelligentSIDC must implement the following methods:

• SetSIDCThreshold (Size): where 100 ≤ Size ≤ ~500,000 is an integer number that defines the size of the list. This size is very important as it will determine what data types or data structures will be used (i.e. a Tree, Hash Table, AVL tree, binary tree, sequence, etc.);

• generate(): randomly generates new non-existing key of 8 digits;

• allKeys(IntelligentSIDC): return all keys in IntelligentSIDC as a sorted sequence;

• add(IntelligentSIDC,key,value2): add an entry for the given key and value;

• remove(IntelligentSIDC,key): remove the entry for the given key;

• getValues(IntelligentSIDC,key): return the values of the given key;

• nextKey(IntelligentSIDC,key): return the key for the successor of key;

• prevKey(IntelligentSIDC,key): return the key for the predecessor of key;

• rangeKey(key1, key2): returns the number of keys that are within the specified range of the two keys key1 and key2.

