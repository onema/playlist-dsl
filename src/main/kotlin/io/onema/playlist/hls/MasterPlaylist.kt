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

class MasterPlaylist {
    val version = Version()
    val add = this
    private val streams = mutableListOf<MasterStream>()

    infix fun stream(block: MasterStream.() -> Unit) {
        streams.add(MasterStream().apply(block))
    }

    override fun toString(): String = buildString {
        appendLine(ExtM3U())
        appendLine(version)
        streams.forEach {streamInf ->
            appendLine(streamInf)
        }
    }

    class MasterStream {
        var streamInf = StreamInf()
        var info: Path = MediaPath()

        override fun toString(): String = buildString {
            appendLine(streamInf)
            append(info)
        }
    }
}

object Master {
    infix fun playlist(block: MasterPlaylist.() -> Unit): String =
        MasterPlaylist().apply(block).toString().trimEnd()
}

typealias master = Master
