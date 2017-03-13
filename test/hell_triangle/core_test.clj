(ns hell-triangle.core-test
  (:require [clojure.test :refer :all]
            [hell-triangle.core :refer :all]))

(deftest a-test
  (testing "Comecando bateria de testes"
   (is (= 26 (findPath [[6] [3 5] [9 7 1] [4 6 8 4]]))) ;exemplo do enunciado
	(is (= 5 (findPath [[5]]))) ;compativel com problema mínimo
 	(is (= -9 (findPath [[-25] [3 -2] [9 7 1] [4 1 -1 4]]))) ;compativel com numeros negativos
	(is (= 11 (findPath [[1] [1 0] [0 1 0] [0 1 0 10]]))))) ;um algorítmo guloso e ingênuo (a cada linha pegar maior valor) provavelmente responderia 4
