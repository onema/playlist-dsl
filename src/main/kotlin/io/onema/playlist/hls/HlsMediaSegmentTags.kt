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

/**
 * Media Segment tag that specifies the duration fo a media segment.
 *
 * This tag is required for each media segment as specified in the
 * [MediaSegment][MediaPlaylist.MediaSegment] class.
 */
class ExtInf : HlsPlaylistTag() {
    private var duration: Float = 0F
    private var title: String = ""
    override var type: String = "EXTINF"

    infix fun duration(duration: Float)  = apply {
        this.duration = duration
    }

    infix fun title(title: String) = apply {
        this.title = title
    }

    override fun toString(): String {
        builder.append("$duration,$title".trim(','))
        return super.toString()
    }
}

/**
 * The key tag specifies how to decrypt media segments if these are encrypted.
 *
 * At this time, this class **does not construct a valid key** tag for methods other than NONE.
 */
class Key : ExtX() {
    override val type = "KEY"
    var name: String = ""
    var value: String = ""

    infix fun value(value: String) = apply {
        this.value = value
    }

    infix fun name(name: String) = apply {
        this.name = name
    }

    override fun toString(): String {
        builder.append("${name.toUpperCase()}=${value.toUpperCase()}")
        return super.toString()
    }
}

/**
 * Media Segment tag that indicates a media segment is a sub-range of the resource identified
 * by it's URI.
 */
class ByteRange : ExtX() {
    override val type = "BYTERANGE"

    private var length: Int = 0
    private var position: Int = 0

    infix fun length(length: Int): ByteRange = apply {
        this.length = length
    }

    infix fun position(position: Int): ByteRange = apply {
        this.position = position
    }

    override fun toString(): String {
        builder.append("$length@$position")
        return super.toString()
    }
}

/**
 * Simple path/URI line that follows the `EXT-X-BYTERANGE` tag.
 */
class SegmentPath : Path() {
    override val type: String = "segment"
}