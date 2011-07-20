/*
 * Copyright 2011, Stuart Douglas
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.fakereplace.com.google.common.collect;

import org.fakereplace.com.google.common.annotations.GwtCompatible;

import java.util.Map;
import java.util.Set;


/**
 * A bimap (or "bidirectional map") is a map that preserves the uniqueness of
 * its values as well as that of its keys. This constraint enables bimaps to
 * support an "inverse view", which is another bimap containing the same entries
 * as this bimap but with reversed keys and values.
 *
 * @author Kevin Bourrillion
 */
@GwtCompatible
public interface BiMap<K, V> extends Map<K, V> {
    // Modification Operations

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException if the given value is already bound to a
     *                                  different key in this bimap. The bimap will remain unmodified in this
     *                                  event. To avoid this exception, call {@link #forcePut} instead.
     */
    V put(K key, V value);

    /**
     * An alternate form of {@code put} that silently removes any existing entry
     * with the value {@code value} before proceeding with the {@link #put}
     * operation. If the bimap previously contained the provided key-value
     * mapping, this method has no effect.
     * <p/>
     * <p>Note that a successful call to this method could cause the size of the
     * bimap to increase by one, stay the same, or even decrease by one.
     * <p/>
     * <p><b>Warning</b>: If an existing entry with this value is removed, the key
     * for that entry is discarded and not returned.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the value which was previously associated with the key, which may
     *         be {@code null}, or {@code null} if there was no previous entry
     */
    V forcePut(K key, V value);

    // Bulk Operations

    /**
     * {@inheritDoc}
     * <p/>
     * <p><b>Warning:</b> the results of calling this method may vary depending on
     * the iteration order of {@code map}.
     *
     * @throws IllegalArgumentException if an attempt to {@code put} any
     *                                  entry fails. Note that some map entries may have been added to the
     *                                  bimap before the exception was thrown.
     */
    void putAll(Map<? extends K, ? extends V> map);

    // Views

    /**
     * {@inheritDoc}
     * <p/>
     * <p>Because a bimap has unique values, this method returns a {@link Set},
     * instead of the {@link java.util.Collection} specified in the {@link Map}
     * interface.
     */
    Set<V> values();

    /**
     * Returns the inverse view of this bimap, which maps each of this bimap's
     * values to its associated key. The two bimaps are backed by the same data;
     * any changes to one will appear in the other.
     * <p/>
     * <p><b>Note:</b>There is no guaranteed correspondence between the iteration
     * order of a bimap and that of its inverse.
     *
     * @return the inverse view of this bimap
     */
    BiMap<V, K> inverse();
}