(defn div-by-10? [n]
    (zero? (reduce +
        (for [i (range 2 10)]
            (mod n i)))))

(first
    (drop-while #(not (div-by-10? %)) (iterate inc 10)))