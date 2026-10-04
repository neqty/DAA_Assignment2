import pandas as pd
import matplotlib.pyplot as plt
import os

df = pd.read_csv("results/results.csv")

os.makedirs("results/plots", exist_ok=True)

def time_plot(data, title, filename):
    plt.figure()

    for structure in data["structure"].unique():
        part = data[data["structure"] == structure]
        plt.plot(part["n"], part["time_ms"], marker="o", label=structure)

    plt.xscale("log")
    plt.xlabel("n")
    plt.ylabel("Time (ms)")
    plt.title(title)
    plt.legend()
    plt.grid(True)
    plt.tight_layout()
    plt.savefig("results/plots/" + filename)
    plt.close()

def metric_plot(data, metric, title, filename):
    plt.figure()

    for structure in data["structure"].unique():
        part = data[data["structure"] == structure]
        plt.plot(part["n"], part[metric], marker="o", label=structure)

    plt.xscale("log")
    plt.xlabel("n")
    plt.ylabel(metric.capitalize())
    plt.title(title)
    plt.legend()
    plt.grid(True)
    plt.tight_layout()
    plt.savefig("results/plots/" + filename)
    plt.close()

w1 = df[df["workload"] == "W1"]
time_plot(w1, "W1 Random Access - Time", "w1_time.png")
metric_plot(w1, "steps", "W1 Random Access - Steps", "w1_steps.png")

w2 = df[df["workload"] == "W2"]
time_plot(w2, "W2 Search - Time", "w2_time.png")
metric_plot(w2, "steps", "W2 Search - Steps", "w2_steps.png")
metric_plot(w2, "comparisons", "W2 Search - Comparisons", "w2_comparisons.png")

w3_head = df[
    (df["workload"] == "W3") &
    (df["variant"] == "head")
]

time_plot(w3_head, "W3 Head - Time", "w3_head_time.png")
metric_plot(w3_head, "steps", "W3 Head - Steps", "w3_head_steps.png")
metric_plot(w3_head, "moves", "W3 Head - Moves", "w3_head_moves.png")

w3_middle = df[
    (df["workload"] == "W3") &
    (df["variant"] == "middle")
]

time_plot(w3_middle, "W3 Middle - Time", "w3_middle_time.png")
metric_plot(w3_middle, "steps", "W3 Middle - Steps", "w3_middle_steps.png")
metric_plot(w3_middle, "moves", "W3 Middle - Moves", "w3_middle_moves.png")

w4 = df[df["workload"] == "W4"]
time_plot(w4, "W4 Priority Processing - Time", "w4_time.png")
metric_plot(w4, "steps", "W4 Priority Processing - Steps", "w4_steps.png")
metric_plot(w4, "moves", "W4 Priority Processing - Moves", "w4_moves.png")
metric_plot(w4, "comparisons", "W4 Priority Processing - Comparisons", "w4_comparisons.png")

print("Plots created")