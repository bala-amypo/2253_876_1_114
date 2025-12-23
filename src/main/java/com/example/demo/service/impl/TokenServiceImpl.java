public TokenServiceImpl(
        TokenRepository tokenRepository,
        ServiceCounterRepository serviceCounterRepository,
        TokenLogRepository tokenLogRepository,
        QueuePositionRepository queuePositionRepository) {
    this.tokenRepository = tokenRepository;
    this.serviceCounterRepository = serviceCounterRepository;
    this.tokenLogRepository = tokenLogRepository;
    this.queuePositionRepository = queuePositionRepository;
}
