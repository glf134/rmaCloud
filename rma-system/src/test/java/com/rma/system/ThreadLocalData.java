package com.rma.system;

/**
 * @author glf
 */
public class ThreadLocalData {

    private static final InheritableThreadLocal<Integer> seqNum = new InheritableThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    // ②获取下一个序列值
    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }
}
