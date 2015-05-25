package com.chr.cache;

import java.util.Collection;
import java.util.Set;

/**
 *
 * @author zhenglu
 * @since 15/5/22
 */
public interface Cache<K,V> {

    public V get(K key);

    public V set(K key,V value);

    public V remove(K key);

    public void clear();

    public Set<K> keys();

    public Collection<V> values();

}
