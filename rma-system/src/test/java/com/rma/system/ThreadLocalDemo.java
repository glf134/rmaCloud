package com.rma.system;

/**
 * @author glf
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocalData threadLocalData = new ThreadLocalData();
        // ③ 3个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(threadLocalData);
        TestClient t2 = new TestClient(threadLocalData);
        TestClient t3 = new TestClient(threadLocalData);
        t1.start();
        t2.start();
        t3.start();
    }

    private static class TestClient extends Thread {
        private ThreadLocalData sn;

        public TestClient(ThreadLocalData sn) {
            this.sn = sn;
        }

        public void run() {
            for (int i = 0; i < 3; i++) {
                // ④每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["
                        + sn.getNextNum() + "]");
            }
        }
    }
}
