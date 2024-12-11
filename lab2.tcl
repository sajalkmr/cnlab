set ns [new Simulator]
set nf [open lab2.tr w]
$ns namtrace-all $nf
set tf [open lab2.nam w]
$ns namtrace-all $tf

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

$n4 shape box

$ns duplex-link $n0 $n4 1Mb 1ms DropTail
$ns duplex-link $n1 $n4 5Mb 1ms DropTail
$ns duplex-link $n2 $n4 2Mb 1ms DropTail
$ns duplex-link $n3 $n4 2Mb 1ms DropTail
$ns duplex-link $n4 $n5 1Mb 1ms DropTail

set p1 [new Agent/Ping]
set p2 [new Agent/Ping]
set p3 [new Agent/Ping]
set p4 [new Agent/Ping]
set p5 [new Agent/Ping]

$ns attach-agent $n0 $p1
$ns attach-agent $n1 $p2
$ns attach-agent $n2 $p3
$ns attach-agent $n3 $p4
$ns attach-agent $n5 $p5

Agent/Ping instproc recv {from rtt} {
    $self instvar node_
    puts "node [$node_ id] received answer from $from with round trip time $rtt msec"
}

$ns connect $p1 $p5
$ns connect $p3 $p4
$ns connect $p2 $p5

proc finish {} {
    global ns nf tf
    $ns flush-trace
    close $tf
    close $nf
    exec nam lab2.nam &
    exec echo "No of packets dropped" &
    exec grep -c "^d" lab2.nam &
    exit 0
}

$ns at 0.1 "$p1 send"
$ns at 0.2 "$p1 send"
$ns at 0.3 "$p1 send"
$ns at 0.4 "$p1 send"
$ns at 0.5 "$p1 send"
$ns at 0.1 "$p2 send"
$ns at 0.2 "$p2 send"
$ns at 0.1 "$p3 send"
$ns at 0.2 "$p3 send"
$ns at 0.7 "finish"

$ns run
