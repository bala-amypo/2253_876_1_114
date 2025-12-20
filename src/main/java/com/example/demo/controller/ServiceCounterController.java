@RestController
@RequestMapping("/counters")
@Tag(name = "Service Counter Controller")
public class ServiceCounterController {

    private final ServiceCounterService service;

    public ServiceCounterController(ServiceCounterService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<ServiceCounter> addCounter(@RequestBody ServiceCounter counter) {
        return ResponseEntity.ok(service.addCounter(counter));
    }

    @GetMapping("/active")
    public ResponseEntity<List<ServiceCounter>> getActiveCounters() {
        return ResponseEntity.ok(service.getActiveCounters());
    }
}
