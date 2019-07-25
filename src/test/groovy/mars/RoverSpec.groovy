package mars


import spock.lang.Specification
import spock.lang.Unroll

import static Orientation.EAST
import static Orientation.NORTH
import static Orientation.SOUTH
import static Orientation.WEST

class RoverSpec extends Specification {

//    def "cannot create a rover with non cardinal direction"() {
//        expect:
//        new Rover(new Point(1,2), 'X')
//
//    }

    def "a rover can be created and knows where it is"() {
        def rover = new Rover(new Point(1, 2), NORTH)
        expect:
        rover.position == new Point(1, 2)
        rover.orientation == NORTH

    }

    @Unroll("the rover can turn #turn from #startingDirection to #expectedDirection")
    def "the rover can turn"() {
        when:
        def rover = new Rover(new Point(1,1), startingDirection)
        rover.drive(turn)

        then:
        rover.orientation == expectedDirection


        where:
        startingDirection | turn | expectedDirection
        NORTH             | 'l'  | WEST
        SOUTH             | 'l'  | EAST
        EAST              | 'l'  | NORTH
        WEST              | 'l'  | SOUTH
        NORTH             | 'r'  | EAST
        SOUTH             | 'r'  | WEST
        EAST              | 'r'  | SOUTH
        WEST              | 'r'  | NORTH
    }

    @Unroll("when facing #rover.orientation driving forwards will move from #rover.position to #expectedPosition.position")
    def "the rover can move forwards"() {
        when:
        rover.drive("f")

        then:
        rover == expectedPosition

        where:
        rover                             | expectedPosition
        new Rover(new Point(0,0), NORTH)  | new Rover(new Point(0,1), NORTH)
        new Rover(new Point(0,0), SOUTH)  | new Rover(new Point(0,-1), SOUTH)
        new Rover(new Point(0,0), EAST)   | new Rover(new Point(1,0), EAST)
        new Rover(new Point(0,0), WEST)   | new Rover(new Point(-1,0), WEST)
    }

    @Unroll("when facing #rover.orientation driving backwards will move from #rover.position to #expectedPosition.position")
    def "the rover can move backwards"() {
        when:
        rover.drive("b")

        then:
        rover == expectedPosition

        where:
        rover                             | expectedPosition
        new Rover(new Point(1,1), NORTH)  | new Rover(new Point(1,0), NORTH)
        new Rover(new Point(1,1), SOUTH)  | new Rover(new Point(1,2), SOUTH)
        new Rover(new Point(1,1), EAST)   | new Rover(new Point(0,1), EAST)
        new Rover(new Point(1,1), WEST)   | new Rover(new Point(2,1), WEST)
    }

    def "can drive the rover with multiple commands"() {
        given:
        def rover = new Rover(new Point(0,0), NORTH)

        when:
        rover.command("f,l,r,l,f,f")

        then:
        rover == new Rover(new Point(-2, 1), WEST)
    }


}
