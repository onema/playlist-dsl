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

import io.onema.playlist.hls.PlaylistTypeEnum.VOD
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class MasterPlaylistTest {

    @Test
    fun `Assert simple master playlist`() {
        // Arrange - Act
        val master = master playlist {
            version set 5
            add stream {
                streamInf resolution  "resolution1" codecs  "codec" bandwidth 12345 frameRate  123F
                info name "videoName" rendition "rendition"
            }
        }
        val expectation = """#EXTM3U
                                   |#EXT-X-VERSION:5
                                   |#EXT-X-STREAM-INF:RESOLUTION=resolution1,CODECS="codec",BANDWIDTH=12345,FRAME-RATE=123.000
                                   |media/rendition""".trimMargin()

        // Assert
        assertEquals(expectation, master, "Master playlist didn't match expected value")
    }
    @Test
    fun `Assert simple media playlist`() {
        // Arrange - Act
        val media = media playlist {
            version set 5
            type set VOD
            mediaSequence set 0
            targetDuration set 6
            method value "NONE"
            add segment {
                extInf duration 2.0F
                byteRange length 123 position 321
                info name "videoName" rendition "rendition"
            }
        }
        val expectation = """#EXTM3U
                                   |#EXT-X-VERSION:5
                                   |#EXT-X-PLAYLIST-TYPE:VOD
                                   |#EXT-X-MEDIA-SEQUENCE:0
                                   |#EXT-X-TARGETDURATION:6
                                   |#EXT-X-KEY:METHOD=NONE
                                   |#EXTINF:2.0
                                   |#EXT-X-BYTERANGE:123@321
                                   |segment/rendition
                                   |#EXT-X-ENDLIST""".trimMargin()

        // Assert
        assertEquals(expectation, media, "Media playlist didn't match expected value")
    }
}