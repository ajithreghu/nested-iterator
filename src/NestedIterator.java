import java.util.*;

public class NestedIterator<T> implements Iterator<T> {

    private final List<Iterator<T>> itrList;

    private int currIndex = -1;

    private boolean mustRecomputeIndex = true;

    public NestedIterator(Iterator<T>... iterators) {
        itrList = new ArrayList<>();
        if(iterators != null) {
            itrList.addAll(Arrays.asList(iterators));
        }
    }

    /**
     * Takes care of the following:
     *
     *   1. O(N) execution time for hasNext() and use that information in next()
     *   2. Removes empty iterators - Two advantages:
     *          a. No need of Unnecessary iterations of empty iterators
     *          b. Makes the code/logic much cleaner
     *
     *   3. Idempotency of hasNext
     *
     *  Caveat: Access/Update of state variable "mustRecomputeIndex" is not threadsafe. But, this can be achieved
     *  by implicit/explicit locks appropriately.
     *
     * @return
     */
    @Override
    public boolean hasNext() {

        if(itrList.isEmpty()) {
            return false;
        }

        if(mustRecomputeIndex) {

            int nextIndex = (currIndex + 1) % itrList.size();
            while (!itrList.isEmpty()) {
                if (itrList.get(nextIndex).hasNext()) {
                    break;
                }
                itrList.remove(nextIndex);
            }

            currIndex = nextIndex;
            mustRecomputeIndex = false;
            return !itrList.isEmpty();

        } else {

            return itrList.get(currIndex).hasNext();

        }
    }


    @Override
    public T next() {

        if(!hasNext()) {
            throw new NoSuchElementException();
        }

        T next = itrList.get(currIndex).next();
        mustRecomputeIndex = true;
        return next;

    }
}
