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

import io.onema.playlist.hls.MediaPlaylist.MediaSegment

class MediaPlaylist {
    val version: Version = Version()
    val type: PlaylistType = PlaylistType()
    val mediaSequence: MediaSequence = MediaSequence()
    val targetDuration: TargetDuration = TargetDuration()
    val method: Key = Key() name "METHOD" value "NONE"

    private val segments = mutableListOf<MediaSegment>()

    val add = this

    infix fun segment(block: MediaSegment.() -> Unit) {
        segments.add(MediaSegment().apply(block))
    }

    override fun toString(): String = buildString {
        appendLine(ExtM3U())
        appendLine(version)
        appendLine(type)
        appendLine(mediaSequence)
        appendLine(targetDuration)
        appendLine(method)
        segments.forEach { segment ->
            appendLine(segment)
        }
        appendLine(EndList())
    }

    class MediaSegment {
        var extInf = ExtInf()
        val byteRange = ByteRange()
        var info: Path = SegmentPath()

        override fun toString(): String  = buildString {
            appendLine(extInf)
            appendLine(byteRange)
            append(info)
        }
    }
}

object Media {
    infix fun segment(block: MediaSegment.() -> Unit): MediaSegment =
        MediaSegment().apply(block)

    infix fun playlist(block: MediaPlaylist.() -> Unit): String =
        MediaPlaylist().apply(block).toString().trimEnd()
}

typealias media = Media
