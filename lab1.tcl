set ns [new Simulator]
set tf [open lab1.tr w]
$ns trace-all $tf
set nf [open lab11.nam w]
$ns namtrace-all $nf

set n0 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

$ns duplex-link $n0 $n2 1000Mb 10ms DropTail
$ns duplex-link $n2 $n3 0.001Mb 10ms DropTail

$ns set queue-limit $n0 $n2 5
$ns set queue-limit $n2 $n3 1

set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0

set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0

set null [new Agent/Null]
$ns attach-agent $n3 $null

$ns connect $udp0 $null
$cbr0 set packetSize_ 5Mb
$cbr0 set interval_ 0.005

proc finish {} {
    global ns nf tf
    $ns flush-trace
    exec nam lab11.nam &
    exec echo "Number of Packets dropped :" &
    exec grep -c "^d" lab11.nam &
    close $tf
    close $nf
    exit 0
}

$ns at 0.1 "$cbr0 start"
$ns at 10.0 "finish"

$ns run
