(ns menu.core)
(require '[clojure.string :as str])
(require '[clojure.set :as set])

(def filename "/Users/petebrooks/code/menu/spec/clj/menu/test_menus/easy_menu.txt")

(defn parse-item [item]
  (let [[item-name price] (str/split item #",\$")]
    {:name item-name
     :price (read-string price)}))

(defn parse-menu [filepath]
  (let [[raw-target & raw-items] (str/split (slurp filepath) #"\n")]
    [(read-string (re-find #"[\d\.]+" raw-target))
     (map parse-item raw-items)]))

(defn match? [target items]
  (= target (reduce + (map :price items))))

(defn max-count [target items]
  (int (/ target (apply min (map :price items)))))

(defn combinations [items n]
  (when-let [[x & xs] items]
    (if (= n 1)
      (map list items)
      (concat (map (partial cons x) (combinations items (dec n)))
              (combinations xs n)))))

(defn combinations-up-to [n items]
  (flatten (map (partial combinations items) (range n))))

(defn select-matches [combos target]
  (set/select (partial match? target) (set combos)))

(defn find-combos [items target max-n]
  (select-matches (combinations items max-n) target))

(defn process-menu [filepath]
  (let [[target items] (parse-menu filepath)
        max-n (max-count target items)]
    (map #(find-combos items target %) (range 1 (+ max-n 1)))))

  ;;     (map #(select-matches (combinations items %) target) (range max-n)))



(process-menu filename)
(combinations t-items 7)




(def t-items (last (parse-menu filename)))
(combinations-up-to 1 t-items)
(max-count 15.05 t-items)
(apply min (map :price t-items))
(reduce + (map :price t-items))
(match? (float 1) t-items)
