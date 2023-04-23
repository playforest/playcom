package com.serialplotter.server.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="api/v1/streams")
public class StreamController {
    private final StreamService streamService;

    @Autowired
    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping(path="/")
    public List<Stream> getStreams() {
        return streamService.getStreams();
    }

    @GetMapping(path="/{streamId}")
    public Stream getStream(@PathVariable Long streamId) {
        return streamService.getStream(streamId);
    }

    @PostMapping()
    public Stream createStream(@RequestBody Stream stream) {
        streamService.insertStream(stream);
        return stream;
    }

    @PutMapping(path="/{streamId}")
    public void updateStream(@PathVariable Long streamId, @RequestBody Stream stream) {
        streamService.updateStream(streamId, stream);
    }

    @PatchMapping(path="/{streamId}")
    public void patchStream(@PathVariable Long streamId, @RequestBody Map<String, Object> streamUpdates) {
        streamService.partialUpdateStream(streamId, streamUpdates);
    }

    @DeleteMapping(path="/{streamId}")
    public void softDeleteStream(@PathVariable Long streamId) {
        streamService.softDeleteStream(streamId);
    }

    @DeleteMapping(path="/hard-delete/{streamId}")
    public void hardDeleteStream(@PathVariable Long streamId) {
        streamService.hardDeleteStream(streamId);
    }


}
