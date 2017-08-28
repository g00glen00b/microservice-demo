package be.g00glen00b.service.data;

import org.springframework.transaction.support.TransactionSynchronization;

public interface AfterCommitTransactionSynchronization extends TransactionSynchronization {
    @Override
    default void suspend() {};

    @Override
    default void resume() {};

    @Override
    default void flush() {};

    @Override
    default void beforeCommit(boolean b) {};

    @Override
    default void beforeCompletion() {};

    @Override
    default void afterCompletion(int i) {};
}
