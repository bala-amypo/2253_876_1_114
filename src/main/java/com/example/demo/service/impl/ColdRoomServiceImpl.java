@Service
public class ColdRoomServiceImpl implements ColdRoomService {

    private final ColdRoomRepository repo;

    public ColdRoomServiceImpl(ColdRoomRepository repo) {
        this.repo = repo;
    }

    @Override
    public ColdRoom create(ColdRoom room) {
        if (room.getMinAllowed() == null || room.getMaxAllowed() == null ||
            room.getMinAllowed() >= room.getMaxAllowed()) {
            throw new IllegalArgumentException("Invalid range");
        }
        return repo.save(room);
    }
}
