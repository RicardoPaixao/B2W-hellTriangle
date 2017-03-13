;This is the solution for the 'hell Triangle' challenge. Used as part of B2W's recruitment process
;
;The language of choice was Clojure. It was chosen both because JVM is very flexible and Clujure's linear programming structure
;make solving purely mathematical problem very intuitive and neat. This makes Clojure a great choice.
;
;A very straightforward approach to the challenge is the brute-force method, testing every combination of triangle sums.
;This, however leads to a O(2^n) resolution time. The pascal triangle show us the total number of possible combination to reach each item.
;
;Another possible (however incorrect) approach would be to select the very best option each step of the way and discard all other options.
;This would reduce the resolution time from O(2^n) to a more hearthy O(n^2), but would also shadow significant raises on the outcome that may rise on the latter line of the triangle.
;
;Take, for example:
;1
;1 0
;1 0 10
;the correct answer would be 11, but a best-option-each-way approach would return a 3.
;
;However, the rule “An element can only be summed with one of the two nearest elements in the next row”
;also mean that any element of the maximum path was summed with one of its nearest parents, since this rules applied to both the parents.
;The chosen approach was to find the greatest sum that could lead to each element. Line by line, and replace each element with its greatest sum so far.
;(the former lines where also discarded since the would no longer be of use)
;When there where no more lines to process. The answer would be the greatest element on the last line. This approach would produce the following result
;
;6			
;3-5	=>		9-11			
;9-7-1			9-7-1	=>		18-18-12
;4-6-8-4 		4-6-8-4 		4-6-8-4		=>	22-24-26-16	=>	Answer 26 

(ns hell-triangle.core
  (:gen-class))

(require '[clojure.test :as test])

;if the matrix has just one line left, the processing has ended, and we can get the result.
(defn acabou? [triangle]   (= nil (get triangle 1)))

;when the triangle has just on line left, the result will be the maximun value among the elements of this line.
(defn getResposta [triangle]
	(apply max (get triangle 0)))

;each processing step only involves the first 2 lines of the triangle
;we must add each elemento of line 1 with the largest of its parents (and ignore the other parent)
(defn step [triangle]
	(map-indexed 
		(fn [idx itm] 
			(+ 
				itm 
				(apply max 
					(remove nil? [(get (get triangle 0) (- idx 1)) 
					(get (get triangle 0) idx)]))))
		(get triangle 1)))

;main function, verifies if the processing is over, if it is, fetches the answer, if not, keeps on processing
(defn findPath [triangle]
		(if (acabou? triangle)
	    	(getResposta triangle)
			(recur (into [] (concat ;tail recursion!
				[(into [] (step triangle))] ;fetches the processed array, that represents the first 2 lines of the triangle, added together with maximin output
				(subvec triangle 2)))))) ;discards the firts 2 lines of the old matrix and appends it to the new array, to form a new matrix, one line shorter 

;the tests are in core_test.clj

(defn -main
  [& args])