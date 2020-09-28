/**
 * This file is part of the ONEMA playlist-dsl Package.
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed
 * with this source code.
 *
 * copyright (c) 2020, Juan Manuel Torres (http://onema.io)
 *
 * @author Juan Manuel Torres <software@onema.io>
 */
package io.onema.playlist.hls

import java.lang.StringBuilder

/**
 * Abstract tags to specify global parameters of the playlist.
 *
 * All child classes must define the type property.
 */
abstract class HlsPlaylistTag {
    abstract val type: String
    open val separator: String
        get() = ":"
    protected val builder = StringBuilder()

    override fun toString(): String {
        builder.insert(0, "#${this.type}${this.separator}")
        return builder.toString().trimEnd(',')
    }
}

/**
 * Abstract tag of all #EXT-X tags
 */
abstract class ExtX : HlsPlaylistTag() {
    override fun toString(): String {
        builder.insert(0, "#EXT-X-${this.type}:")
        return builder.toString().trimEnd(',')
    }
}

/**
 * Abstract class to enable the syntax;
 * ```
 * val tag = SingleValueTag()
 * tag set value
 * ```
 */
abstract class SingleValueExtX<T> : ExtX() {
    open var value: T? = null

    infix fun set(value: T) {
        this.value = value
    }

    override fun toString(): String {
        builder.append(value)
        return super.toString()
    }
}

/**
 * Class that defines a relative path.
 *
 * Because paths are relatives media segments are defined as
 * ```
 * media/320x180.m3u8
 * ```
 *
 * and a segment
 *
 * ```
 * segment/320x180.ts
 * ```
 * If the names are `media`, `segment` and the renditions are
 * `320x180.m3u8` and `320x180.ts` respectively.
 *
 * this will result in the following absolute paths:
 * ```
 * /path/to/video/media/320x180.m3u8
 * /path/to/video/media/segment/320x180.ts
 * ```
 */
abstract class Path {
    private var name: String = ""
    private var rendition: String = ""
    abstract val type: String

    infix fun name(name: String): Path = apply {
        this.name = name
    }

    infix fun rendition(rendition: String): Path = apply {
        this.rendition =rendition
    }

    override fun toString(): String  = buildString {
        append("$type/$rendition")
    }
}
