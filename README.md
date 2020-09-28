# playlist-dsl

## Install
In your dependencies:
```
implementation("io.onema:playlist-dsl:0.1.0")
```

## Example
### Master Playlist
```kotlin
import io.onema.manifestservice.playlist.master

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
assertEquals(expectation, master)
```

### Media Playlist
```kotlin
import io.onema.manifestservice.playlist.PlaylistTypeEnum.VOD
import io.onema.manifestservice.playlist.media

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
assertEquals(expectation, media, "Media playlist didn't match expected value")
```

## Reference
**Most of the docblock text is taken from or is praphrased of the [RFC8216 - HTTP Live Streaming](https://tools.ietf.org/html/rfc8216).**