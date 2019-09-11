; Basic example of a Clojure form / expression: A line of code (....)
(println "Hello, world!")

; Constants are called symbols in Clojure. Below will show data type of a: Symbol
; ' will prevent a from from being evaluated
(type 'a)

; ============== Bindings ===============
; Clojure is a Lisp, and will evaluate everything as lists, evaluating the contents of a list left to right.
; Binding (def) a value ("aaaaa") to a symbol (a) within a scope (default: user) and printing it.
(def a "aaaaa")
(println a)

; Printing a Symbol with no value results in an error.
(println b)

; A Lisp will evualate a list of lists left to right.
; Binding a value to a symbol within a private variable scope (let) and printing it concated (println (str)).
; let takes a vector: [symbol, value]
(let [l "light"] (println (str "God said let there be " l)))

; These will not resolve, as a is referenced before it is bound a value. 
((println (str "Right to left? " a) let [a "Yes"]))
((println (str "Right to left? " a) (def a "Yes")))

; The symbol a is referenced outside of the let, which means it is not in scope. 
(let [n "No"])
(println (str "Will this resolve? " n))

; let allows multiple bindings, and you can reference previous bindings
(let [l "light"
    l_d (str l " and darkness")]
    println (str "God said " l_d))

; Nesting lets will make the innermost Symbol assignement apply to its own scope
; This is also know as it's lexical (words in a sentence) scope
(let [a "aaa"]
    (let [a "AAA"]
        (println a)) ; Will print "AAA"
    (println a)) ; Will print "aaa"

; Clojure will look inside out (expanding scope) to find the first applying symbol assignement
(let [a "AAA"]
    (let []
        (println a)))

; Note: using Def will scope in namespace (user) instead of in the lexical scope, and should be avoided if you do not want to introduce state.

; ============== Functions ===============
; A function is built up with the following (optional*) elements:
; defn nameOfFunction
; "Documentation"* - Readable with the doc function: (doc nameOfFunction)
; {:metadata "metadata"}* - Readable with the meta function as passed the func as a var: (meta (var NameofFunction))
; [arguments]
; Function body

(defn say-hello
    "Function that says hello to a variable name"
    {:author "Luuk"}
    [name]
    (println (str "Hello " name)))

(say-hello "Bob")

; Functions are first class objects in Clojure, and can be created using fn
(fn [] (println "Hello world"))

; As they are objects, they can be bound to a variable. 
(def hello-func (fn [] (println "Hello")))
(hello-func)

; #() is shorthand for (fn []). You can use % to be replaced with arguments. If multiple: %1 %2 etc
#(+ 1 %)
(let [plus-multiple #(+ 1 %1 %2)]
    (plus-multiple 10 20))

; Functions can be passed to other functions.
; A(n inner) function (say-something) that does something with an argument from an outer function (greeting) is called a closure.
(def say-something (fn [name say] (println (str say name))))
(def greeting (fn [name] (say-something name "Hi there, ")))
(greeting "Bob")

; ============== Namespaces ===============
; A namespace is a grouping of symbols used to organize related objects. Comparable to a module. 
; We create a namespace with create-ns. We use the backtick so the symbol is not resolved. We move to a namespace with in-ns.
; Things defined in a namespace are not available from other namespaces.
(create-ns 'clojure.by.example)
(in-ns 'clojure.by.example)

(defn favorite-language [] "Clojure!")
(favorite-language)
(create-ns 'second)
(in-ns 'second)
(favorite-language)
(all-ns)

; To bring a namespace into the environment, we use require. We can alias them too (in a vector), and require multiple. 
(require 'clojure.by.example)
(clojure.by.example/favorite-language)

(require '[clojure.by.example :as cbe]
    '[clojure.core: as ccc])
(cbe/favorite-language)

; Refer will add contents of a namespace to environment without having to type the namespace. Use will combine require and refer.
(refer 'clojure.by.example)
(favorite-language)

(use 'clojure.by.example)

; To get a namespace from java, use import
(import java.until.Date)
(new Date)

; NS
; ns is a macro that creates a new namespace that can take the :require, :use, and :import keyword to skip separate steps above. It will also include more core. 
(ns example.namespace
    (:require [clojure.java.io])
    (:use [clojure.data])
    (:import [java.util List Set]))

; ============== Control Flow ===============
; IF takes a predicate, an true condition and an optional false / else condition. 
(if true
    (println "True!")
    (println "False"))

; To make multiple things happen in a condition, use DO
(if true 
    (do 
    (println "True!")
    (println "Still true!"))
    (println "False!"))

; if-let is an if statement combined with a variable assignement when a statement is truthy
; Below uses the not-empty test combined with a (filter pos?) test
; If we only use the filter and not not-empty, we would get an empty list which is not in fact falsey
; not-empty return nil for an empty list, which is falsey.
(defn pos-numbers [numbers]
    (if-let [posi-nums (not-empty (filter pos? numbers))] ; Filter numbers if pos? numbers is truthy into posi-nums vector
        posi-nums ; Resulting data (if truthy test)
        "No positive numbers found.")) ; else
(pos-numbers [1 2 -3 -4])
(pos-numbers [-1 -2])

; If you are not interested in an else, you can use when instead of if
; When will do all content if true
; When-let also exists
(when true
    (println "True")
    (println "Also true"))

; For finding a matching branch in a comparitive expression, use case
(defn checkdigit [n]
    (case n
    1 "n is 1"
    2 "n is 2"
    (println "Not one or two")))
(checkdigit 1)

; For matching different if statement conditions, use cond.
; Cond accepts the :else keyword
(defn cat-num [n]
    (cond
        (= n 1) "n is 1"
        (and (> n 3)(< n 10)) "n is between 3 and 10"
        :else "uncategorized"
            ))
(cat-num 1)
(cat-num 5)
(cat-num 11)

; You can use a predicate with condp for condition. In this case contains? is the predicate.
; Todo: This is tricky to understand currently, must test with.
(defn condp-test-2
    [n]
    (condp contains? n
      #{1 2 3} "n is either 1 or 2 or 3"
      "n is not 1 or 2 or 3"))
(println (condp-test-2 2))

; ============== Boolean ===============
; true and false are values of the boolean type
; Everything in clojure is true except false and nil
(boolean 0) ; true
(boolean 1) ; true
(boolean "text") ; true

; ============== Strings ===============
; Strings are between double quotes, and can should be concatenated by str. + will not work.
(str "Hello " "world" "!")

; Use format to format a string, with the arguments %s for string, %d for integer, %.Nf for float (N is number of digits), %b is for boolean
(format "The %s has %d apples, that much is %b." "farmer" 3 true)

; ============== Integers ===============
; operators are as you would expect: + - * / 
; interesting is that / can result in a ratio
(/ 4 3) ; => 4/3

; Advanced operators include min, max, mod
; Clojure makes you define your own power operator using reduce. reduce sums a sequence generated by repeat against the * operator
(defn power [x n]
    (reduce * (repeat n x)))

(power 2 4)

; ============== Lists ===============
; Basic data type of a lisp, but in clojure not frequenty used as a data collection unit. Add to lists with conj(oin)
; ' is used to stop evaluation of the list
'(1 2 3 4) 
(conj '(1 2 3) 4)

; Due to the way Clojure works you can not simply remove an item from a list. You can however find an element with nth or count values
(nth '(1 2 3) 1) ; => 2
(count '(1 2 3))

; ============== Vectors ===============
; A better type of list to use for data storage. Vectors are not inherently evaluated
[1 2 3 4]

; vectors have conj, nth, and count, but also first, last and .indexOf (java interop; returns -1 if not found)
(first [1 2 3 4])
(.indexOf [1 2 3 4] 2)

; ============== Sets ===============
; A set is another variant to a list, with only unique values. 
; It takes conj (and will ignore additional values already in the set).
; Sets allow removal with disj(oin), sort, and the contains? test.
#{1 2 3 4}
(disj #{1 2 3 4} 1)
(sort #{1 4 2 3})
(contains? #{1 2 3 4} 1)

; ============== Maps ===============
; A map is a list with a key value structure. All kind of different data types are ok for keys, but usually one uses keyword
{:firstkey "Netherlands" :secondkey "France"}

; You can use get or a keyword used as key to retrieve the value. Not found returns nil
(get {:firstkey "Netherlands" :secondkey "France"} :thirdkey)
(:firstkey {:firstkey "Netherlands" :secondkey "France"})

; assoc will let you add or replace key value pairs. merge will merge
(assoc {:fk "NL" :sk "FR"} :tk "DE")
(merge {:fk "NL" :sk "FR"} {:tk "DE" :frk "BE"})

; Get keys and vals with those keywords
(keys {:fk "NL", :sk "FR", :tk "DE", :frk "BE"})
(vals {:fk "NL", :sk "FR", :tk "DE", :frk "BE"})

; ============== Sequences ===============
