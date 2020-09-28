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
 * The tag indicates that no more media segments will be added to the media playlist file.
 */
class EndList : ExtX() {
    override val type = "ENDLIST"
    override fun toString(): String {
        return super.toString().trimEnd(':')
    }
}

/**
 * The tag Indicates the sequence number of the first media segments in the playlist file
 */
class MediaSequence : SingleValueExtX<Int>() {
    override val type = "MEDIA-SEQUENCE"
}

/**
 * The tag specifies the maximum media segment duration.
 */
class TargetDuration(override var value: Int? = 6) : SingleValueExtX<Int>() {
    override val type = "TARGETDURATION"
}

/**
 * The tag provides mutability information about the media playlist file.
 *
 * It applies to the entire media playlist file and it is optional.
 *
 * It's values are defined by the [PlaylistTypeEnum]
 */
class PlaylistType : SingleValueExtX<PlaylistTypeEnum>() {
    override val type = "PLAYLIST-TYPE"
}

enum class PlaylistTypeEnum {
    EVENT,
    VOD
}
