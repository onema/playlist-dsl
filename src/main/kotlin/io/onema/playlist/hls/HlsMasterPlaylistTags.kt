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
 * Specifies a variant stream,
 *
 * This is a set of renditions that can be combined to play the  presentation.
 * For additional information see the [EXT-X-STREAM-INF](https://tools.ietf.org/html/rfc8216#section-4.3.4.2) documentation.
 */
class StreamInf : ExtX() {
    override val type = "STREAM-INF"

    /**
     * The resolution describing the optimal pixel resolution at
     * which to display all the video in the variant stream.
     *
     * This value is optional, but it is recommended.
     */
    infix fun resolution(resolution: String): StreamInf = apply {
        builder.append("RESOLUTION=$resolution,")
    }

    /**
     * The value is a quoted string containing a comma separated list of formats.
     *
     * Each format specifies a media sample type that is present in one of more renditions.
     */
    infix fun codecs(codecs: String): StreamInf = apply {
        builder.append("""CODECS="$codecs",""")
    }

    /**
     * The value is a decimal integer of bits per second.
     *
     * This represents the peak segment bit rate of the variant stream.
     */
    infix fun bandwidth(bandwidth: Int): StreamInf = apply {
        builder.append("BANDWIDTH=$bandwidth,")
    }

    /**
     * The value is a decimal floating point describing the maximum frame rate for all
     * the video in the variant stream, rounded to three decimal places
     */
    infix fun frameRate(frameRate: Float): StreamInf = apply {
        builder.append("FRAME-RATE=%.3f,".format(frameRate))
    }
}

/**
 * Simple path/URI line that follows the `EXT-X-STREAM-INF` tag.
 */
class MediaPath : Path() {
    override val type: String = "media"
}
