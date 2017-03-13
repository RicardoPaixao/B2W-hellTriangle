# B2W-hellTriangle

This is the solution for the 'hell Triangle' challenge. Used as part of B2W's recruitment process

The language of choice was Clojure. It was chosen both because JVM is very flexible and Clujure's linear programming structure
make solving purely mathematical problem very intuitive and neat. This makes Clojure a great choice.

A very straightforward approach to the challenge is the brute-force method, testing every combination of triangle sums.
This, however leads to a O(2^n) resolution time. The pascal triangle show us the total number of possible combination to reach each item.

Another possible (however incorrect) approach would be to select the very best option each step of the way and discard all other options.
This would reduce the resolution time from O(2^n) to a more hearthy O(n^2), but would also shadow significant raises on the outcome that may rise on the latter line of the triangle.

Take, for example:
1
1 0
1 0 10
the correct answer would be 11, but a best-option-each-way approach would return a 3.

However, the rule “An element can only be summed with one of the two nearest elements in the next row”
also mean that any element of the maximum path was summed with one of its nearest parents, since this rules applied to both the parents.
The chosen approach was to find the greatest sum that could lead to each element. Line by line, and replace each element with its greatest sum so far.
(the former lines where also discarded since the would no longer be of use)
When there where no more lines to process. The answer would be the greatest element on the last line. This approach would produce the following result

6			
3-5		=>	9-11			
9-7-1		9-7-1	=>	18-18-12
4-6-8-4 	4-6-8-4 	4-6-8-4		=>	22-24-26-16	=>	Answer 26 

## Installation

Requires Leiningen (https://leiningen.org/)

## Usage

    $ lein run

## Unity Test

    $ lein test

## Future work

	- develop a simple text-based menu, so the user can run and test the system externally (building a triangular matrix from user input is somewhat laborious);
	- check if the type castings on core.clj:57-58 are really necessary;
	- properlly treat faulty input exceptions cases, like if there is a string or char amidst the matrix.

## License

All rights reserved.