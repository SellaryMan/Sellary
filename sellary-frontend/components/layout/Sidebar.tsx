import Link from "next/link";
import React from "react";
interface SidebarChild {
    name: string;
    path: string;
}
  
interface SidebarParent {
    parent: string;
    name : "상품"
    children: SidebarChild[];
}
type SidebarElementsType = SidebarParent[];

const SidebarElements : SidebarElementsType =[
    {
        parent : "products",
        name : "상품",
        children : [{
                name : "출고상품",
                path : "/outbound"
            },{
                name : "판매상품",
                path : "/sales" 
            }
        ]
    }
]
const Sidebar = () => {
    return (
        <aside className="flex flex-col gap-2 p-4 border-gray-200 border-r-[0.2px] shadow-xs shadow-right bg-gray-200">
        {SidebarElements.map((el: SidebarParent, idx: number) => (
            <div key={idx} className="flex flex-col gap-1">
              <div className="font-medium text-gray-700">{el.name}</div>
              
              <div className="flex flex-col gap-1">
                {el.children.map((child, childIndex) => (
                  <Link 
                    key={childIndex} 
                    href={child.path}
                    className="text-sm text-gray-500 hover:text-gray-800"
                  >
                    {child.name}
                  </Link>
                ))}
              </div>
            </div>
          ))}
        </aside>
    );
  };
  
  export default Sidebar;