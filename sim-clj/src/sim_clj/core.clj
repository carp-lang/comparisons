(ns sim-clj.core
  (:gen-class))

(defrecord Agent [x y])

(defn move-agent [agent]
  (-> agent
      (update :x inc)
      (update :y dec)))

(defn create-world [n]
  (repeatedly n (fn [] (->Agent (rand 100) (rand 100)))))

(defn update-world [world steps]
  ;;(first (drop steps (iterate #(mapv move-agent %) world)))
  ;;(reduce (fn [w _] (mapv move-agent w)) world (range steps))
  (loop [w world
         i steps]
    (if (zero? i)
      w
      (recur (mapv move-agent w) (dec i)))))

(defn -main
  [& args]
  (let [world (create-world 10000)
        world' (update-world world 10000)]
    (println "Done updating" (count world') "agents.")))
