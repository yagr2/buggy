package mars


import spock.lang.Specification

class RoverSpec extends Specification {

//    def "cannot create a rover with non cardinal direction"() {
//        expect:
//        new Rover(new Point(1,2), 'X')
//
//    }

    def "a rover can be created and knows where it is"() {
        def rover = new Rover(new Point(1, 2), "N")
        expect:
        rover.position == new Point(1, 2)
        rover.direction == "N"

    }

    def "the rover can turn"() {
        when:
        def rover = new Rover(new Point(1,1), startingDirection)
        rover.drive(turn)

        then:
        rover.direction == expectedDirection


        where:
        startingDirection | turn | expectedDirection
        'N' | 'l' | 'W'
        'S' | 'l' | 'E'
        'E' | 'l' | 'N'
        'W' | 'l' | 'S'
        'N' | 'r' | 'E'
        'S' | 'r' | 'W'
        'E' | 'r' | 'S'
        'W' | 'r' | 'N'
    }

    def "the rover can move forwards"() {
        when:
        rover.driveForwards()

        then:
        rover == expectedPosition

        where:
        rover                           | expectedPosition
        new Rover(new Point(0,0), 'N')  | new Rover(new Point(0,1), 'N')
        new Rover(new Point(0,0), 'S')  | new Rover(new Point(0,-1), 'S')
        new Rover(new Point(0,0), 'E')  | new Rover(new Point(1,0), 'E')
        new Rover(new Point(0,0), 'W')  | new Rover(new Point(-1,0), 'W')
    }

    def "can drive the rover with multiple commands"() {
        given:
        def rover = new Rover(new Point(0,0), "N")

        when:
        rover.command("f,l,r,l,f,f")

        then:
        rover == new Rover(new Point(-2, 1),"W")
    }


}
