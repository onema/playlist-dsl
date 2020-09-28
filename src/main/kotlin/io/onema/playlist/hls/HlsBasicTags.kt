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
 * Indicates that the file is an extended M3U playlist.
 *
 * This tag must be the first line of any master and media playlists.
 */
class ExtM3U : HlsPlaylistTag() {
    override val type: String = "EXTM3U"
    override val separator: String
        get() = ""
}

/**
 * Indicates the compatibility version of the playlist file.
 *
 * The version tag applies to the entire playlist file
 */
class Version : SingleValueExtX<Int>() {
    override val type = "VERSION"
}