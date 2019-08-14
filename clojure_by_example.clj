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

; ============== Control Flow ===============