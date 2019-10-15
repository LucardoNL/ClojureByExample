; Problem 1: Find the sum of all the multiples of 3 or 5 below 1000.
(reduce 
    + 
    (filter 
        #(or 
            (zero? (mod % 3)) 
            (zero? (mod % 5))
        )
        (range 1000)
    )  
)
; => 233168

; Problem 2: By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.
(reduce 
    + 
    (filter 
        even? 
            (loop [a 0 b 1 c ()] 
                (if (> a 4000000)
                    c
                (recur b (+ a b) (conj c a))
                )
            )
    )  
)
; => 4613732

; Problem 3: What is the largest prime factor of the number 600851475143?
(defn is-prime [n]
    (empty? 
        (filter zero? (map 
            #(mod n %) (range 2 (+ 1 (Math/sqrt n)))
            )
        )
    )
)






