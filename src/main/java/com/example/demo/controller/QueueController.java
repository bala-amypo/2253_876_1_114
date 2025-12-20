@RestController
@RequestMapping("/queue")
@Tag(name = "Queue Controller")
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PutMapping("/position/{tokenId}/{newPosition}")
    public ResponseEntity<QueuePosition> updatePosition(
            @PathVariable Long tokenId,
            @PathVariable Integer newPosition) {
        return ResponseEntity.ok(queueService.updateQueuePosition(tokenId, newPosition));
    }

    @GetMapping("/position/{tokenId}")
    public ResponseEntity<Integer> getPosition(@PathVariable Long tokenId) {
        return ResponseEntity.ok(queueService.getPosition(tokenId));
    }
}
