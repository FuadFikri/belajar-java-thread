package com.syamsudinfikri.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterReadWriteLock {
    public Long value = 0L;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void increment() {
        try {
            lock.writeLock().lock();
            value++;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Long getValue() {
        try {
            lock.readLock().lock();
            return value;
        } finally {
            lock.readLock().unlock();
        }
    }
}
